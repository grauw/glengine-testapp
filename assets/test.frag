precision mediump float;

struct test {
	vec4 color[2];
};

uniform float sinTime;
uniform test color;
varying vec2 v_texCoord;
uniform sampler2D texture;

void main() {
	float mixValue = sinTime / 2.8 + 0.5;
	gl_FragColor = mix(texture2D(texture, v_texCoord), (color.color[0] + color.color[1]), mixValue);
}
